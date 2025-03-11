-- redis_rate_limiter.lua
local key = KEYS[1]
local capacity = tonumber(ARGV[1])
local rate = tonumber(ARGV[2])
local now = tonumber(ARGV[3])
local requested = tonumber(ARGV[4])

-- 获取令牌桶信息
local token_info = redis.call("HMGET", key, "tokens", "timestamp")
local tokens = tonumber(token_info[1])
local timestamp = tonumber(token_info[2])

if tokens == nil then
  tokens = capacity
  timestamp = now
end

local delta = math.max(0, now - timestamp)
local new_tokens = math.min(capacity, tokens + (delta * rate / 1000))
local allowed = new_tokens >= requested
local new_token_count = new_tokens

if allowed then
  new_token_count = new_tokens - requested
end

redis.call("HMSET", key, "tokens", new_token_count, "timestamp", now)
redis.call("PEXPIRE", key, 60000)

return { allowed and 1 or 0, new_token_count }

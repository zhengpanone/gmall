export interface RedisInfo {
  [key: string]: string | undefined;
}

export interface CommandStats {
  calls: number;
  command: string;
  usec: number;
}

export interface CacheInfo {
  commandStats: CommandStats[];
  dbSize: number;
  info: RedisInfo;
}

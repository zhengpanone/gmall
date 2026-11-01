# gmall-module-trade DDD 重构总结

> 基于 DDD（Domain-Driven Design）领域驱动设计原则，对 `gmall-module-trade` 模块进行的完整架构重构。

---

## 一、整体架构

```
┌──────────────────────────────────────────────────────┐
│  Interfaces Layer (接口层)                             │
│  controller/ → REST 适配                              │
│  dto/       → 入参 DTO                               │
│  vo/        → 出参 VO                                │
├──────────────────────────────────────────────────────┤
│  Application Layer (应用层)                           │
│  service/   → 用例编排 & 事务管理                       │
│  command/   → 命令对象                                │
│  query/     → 查询对象                                │
│  assembler/ → DTO ↔ Domain 装配                      │
├──────────────────────────────────────────────────────┤
│  Domain Layer (领域层) ★ 核心                          │
│  model/order/      → 订单聚合 (Order + OrderItem)      │
│  model/valueobject/ → Money, Address, PhoneNumber    │
│  event/            → 领域事件                          │
│  service/          → 领域服务 (OrderDomainService)     │
│  repository/       → 仓储接口 (仅定义)                  │
├──────────────────────────────────────────────────────┤
│  Infrastructure Layer (基础设施层)                      │
│  persistence/entity/   → PO 持久化对象                │
│  persistence/mapper/   → MyBatis Mapper              │
│  persistence/converter/ → Domain ↔ PO 防腐转换         │
│  repository/           → 仓储实现                      │
└──────────────────────────────────────────────────────┘
```

---

## 二、框架层 DDD 基础组件

模块路径：`gmall-framework/gmall-common/src/main/java/com/zp/gmall/framework/common/ddd/`

| 文件 | 类型 | 用途 |
|---|---|---|
| `AggregateRoot<ID>` | 接口 | 聚合根标记，定义一致性边界入口，可获取领域事件列表 |
| `BaseEntity<ID>` | 抽象类 | 实体基类，基于标识的相等性判断（非基于属性） |
| `BaseValueObject` | 抽象类 | 值对象基类，无标识，不可变 |
| `Identifier` | 接口 | 类型安全的标识符接口，替代裸 Long/String |
| `DomainEvent` | 抽象类 | 领域事件基类，包含事件 ID + 发生时间戳 |
| `BaseRepository<T,ID>` | 接口 | 仓储抽象（领域层定义规范，基础设施层实现） |
| `@DomainService` | 注解 | 领域服务标记 |
| `AggregateRootUtils` | 工具类 | 聚合根事件管理能力（组合模式复用，避免基类污染） |

---

## 三、关键 DDD 原则实现

### 3.1 聚合内强一致性

- `Order` 聚合根封装所有状态变更操作（`pay()`, `cancel()`, `ship()`, `addItem()` 等）
- 订单项 `OrderItem` 只能通过聚合根访问，不可独立修改
- 状态流转校验逻辑在领域方法内部执行，外部无法绕过
- 聚合根内部使用 Private 构造函数 + 静态工厂方法控制创建

```
订单状态流转：
  PENDING_PAYMENT → PAID → SHIPPED → COMPLETED
                  ↘ CANCELLED
  SHIPPED → CANCELLED（需要先取消发货）
```

### 3.2 聚合间最终一致性

- 4 个关键领域事件：
  - `OrderCreatedEvent` — 订单创建后发布
  - `OrderPaidEvent` — 支付完成后发布
  - `OrderCancelledEvent` — 订单取消后发布
  - `OrderShippedEvent` — 发货完成后发布
- 应用层在事务提交成功后再发布事件
- 其他限界上下文（库存、支付、物流）异步消费事件，保证最终一致性

### 3.3 依赖倒置原则

- 仓储接口 `OrderRepository` 由**领域层**定义
- 基础设施层 `OrderRepositoryImpl` 实现该接口
- 领域层完全不依赖任何框架（Spring、MyBatis、JPA），保持纯净
- 通过接口隔离，仓储实现可随时替换（MyBatis → JPA → Redis）

### 3.4 核心业务规则归属

| 层次 | 职责 | 示例 |
|---|---|---|
| **领域层** | 订单状态机、金额计算、业务不变式校验 | `Order.pay()`, `Order.canCancel()` |
| **应用层** | 编号生成、事务边界管理、事件发布编排 | `OrderAppService.createOrder()` |
| **基础设施层** | 数据库映射、SQL 执行、外部防腐适配 | `OrderRepositoryImpl`, `OrderPO` |

---

## 四、文件清单

### 4.1 框架层 DDD 基础组件（9 个文件）

| 文件 | 说明 |
|---|---|
| `gmall-common/.../ddd/AggregateRoot.java` | 聚合根标记接口 |
| `gmall-common/.../ddd/Identifier.java` | 标识符接口 |
| `gmall-common/.../ddd/BaseEntity.java` | 实体基类 |
| `gmall-common/.../ddd/BaseValueObject.java` | 值对象基类 |
| `gmall-common/.../ddd/DomainEvent.java` | 领域事件基类 |
| `gmall-common/.../ddd/BaseRepository.java` | 仓储基类接口 |
| `gmall-common/.../ddd/DomainService.java` | 领域服务注解 |
| `gmall-common/.../ddd/AggregateRootUtils.java` | 聚合根事件管理工具 |
| `gmall-common/.../ddd/package-info.java` | 包说明 |

### 4.2 领域层（12 个文件）

#### 聚合、实体与值对象
| 文件 | 说明 |
|---|---|
| `domain/model/order/Order.java` | **核心聚合根**，封装订单全生命周期 |
| `domain/model/order/OrderId.java` | 订单标识符（类型安全 ID） |
| `domain/model/order/OrderItem.java` | 订单项实体 |
| `domain/model/order/OrderItemId.java` | 订单项标识符 |
| `domain/model/order/OrderStatus.java` | 订单状态枚举（含状态流转验证） |
| `domain/model/valueobject/Money.java` | 金额值对象（含货币、不可变算术） |
| `domain/model/valueobject/PhoneNumber.java` | 手机号值对象（含格式校验） |

#### 领域事件
| 文件 | 说明 |
|---|---|
| `domain/event/OrderCreatedEvent.java` | 订单创建事件 |
| `domain/event/OrderPaidEvent.java` | 订单支付事件 |
| `domain/event/OrderCancelledEvent.java` | 订单取消事件 |
| `domain/event/OrderShippedEvent.java` | 订单发货事件 |

#### 领域服务与仓储接口
| 文件 | 说明 |
|---|---|
| `domain/service/OrderDomainService.java` | 订单领域服务 |
| `domain/repository/OrderRepository.java` | 订单仓储接口（领域层定义） |

### 4.3 应用层（4 个文件）

| 文件 | 说明 |
|---|---|
| `application/service/OrderAppService.java` | 订单应用服务（用例编排 + 事务管理） |
| `application/command/CreateOrderCommand.java` | 创建订单命令对象 |
| `application/query/OrderPageQuery.java` | 订单分页查询对象 |
| `application/assembler/OrderAssembler.java` | DTO ↔ Domain 对象装配器 |

### 4.4 基础设施层（7 个文件）

| 文件 | 说明 |
|---|---|
| `infrastructure/persistence/entity/OrderPO.java` | 订单持久化对象 |
| `infrastructure/persistence/entity/OrderItemPO.java` | 订单项持久化对象 |
| `infrastructure/persistence/mapper/OrderPOMapper.java` | 订单 MyBatis Mapper |
| `infrastructure/persistence/mapper/OrderItemPOMapper.java` | 订单项 MyBatis Mapper |
| `infrastructure/persistence/converter/OrderConverter.java` | Domain ↔ PO 双向防腐转换器 |
| `infrastructure/repository/OrderRepositoryImpl.java` | 订单仓储实现 |
| `infrastructure/config/JpaConfig.java` | JPA 配置 |

### 4.5 接口层（6 个文件）

| 文件 | 说明 |
|---|---|
| `interfaces/controller/OrderController.java` | 订单 REST 控制器 |
| `interfaces/dto/CreateOrderRequest.java` | 创建订单请求 DTO |
| `interfaces/dto/PayOrderRequest.java` | 支付订单请求 DTO |
| `interfaces/dto/ShipOrderRequest.java` | 发货请求 DTO |
| `interfaces/vo/OrderDetailVO.java` | 订单详情 VO |
| `interfaces/vo/OrderPageItemVO.java` | 订单分页项 VO |

### 4.6 数据库 DDL（1 个文件）

| 文件 | 说明 |
|---|---|
| `deploy/sql/trade-ddl.sql` | `trade_order` + `trade_order_item` 建表语句 |

### 4.7 清理的废弃文件（3 个）

| 文件 | 原因 |
|---|---|
| `domain/Order.java` | 旧空壳，已迁移至 `model/order/Order.java` |
| `infrastructure/OrderRepository.java` | 旧空壳，已拆分到 `domain/repository/` + `infrastructure/repository/` |
| `application/OrderService.java` | 旧空壳，已替换为 `application/service/OrderAppService.java` |

---

## 五、依赖方向（严格单向）

```
┌─────────────┐
│  Interfaces  │
│   (REST)     │
└──────┬───────┘
       │ 依赖
       ▼
┌─────────────┐
│ Application  │
│ (编排/事务)   │
└──────┬───────┘
       │ 依赖
       ▼
┌─────────────────────────────┐
│     Domain（★ 核心，零依赖）  │
│                              │
│  聚合根 / 实体 / 值对象       │
│  领域事件 / 领域服务          │
│  仓储接口（仅定义，不依赖实现） │
└──────────────┬──────────────┘
               │ 实现
               ▼
┌─────────────┐
│Infrastructure│
│ (持久化/防腐) │
└─────────────┘
```

- `Interfaces → Application`：仅通过 Command/Query 对象调用
- `Application → Domain`：编排领域对象，不包含核心业务规则
- `Infrastructure → Domain`：实现仓储接口，领域层不受基础设施变化影响
- `Domain ↛ 外部依赖`：领域层不依赖任何框架层，可独立单元测试

---

## 六、合计统计

| 分层 | 新建文件 | 关键产出 |
|---|---|---|
| 框架层 DDD 基础组件 | 9 | AggregateRoot, BaseEntity, DomainEvent 等 |
| 领域层 | 12 | Order(聚合根), OrderStatus, Money, 领域事件 |
| 应用层 | 4 | OrderAppService, OrderAssembler, Command |
| 基础设施层 | 7 | OrderPO, OrderRepositoryImpl, OrderConverter |
| 接口层 | 6 | OrderController, Request/Response DTOs |
| DDL | 1 | trade_order + trade_order_item 建表脚本 |
| **合计** | **39** | |

---

## 七、设计要点回顾

1. **聚合设计**：`Order` 是唯一的聚合根，`OrderItem` 是其内部实体，所有对订单项的修改必须通过订单聚合根
2. **值对象**：`Money`、`PhoneNumber` 等为不可变值对象，自带校验逻辑，杜绝贫血模型
3. **标识符**：使用 `OrderId`、`OrderItemId` 类型安全标识符，替代裸 `Long/String`
4. **领域事件**：在聚合根行为方法内创建事件，通过 `AggregateRootUtils` 收集，事务提交后由应用层统一发布
5. **仓储模式**：领域层定义接口，基础设施层实现，通过 `reconstruct()` 工厂方法从持久化数据重建领域对象
6. **防腐层**：`OrderConverter` 负责 Domain ↔ PO 双向转换，隔离数据库模型与领域模型
7. **应用服务**：`OrderAppService` 负责事务管理、ID 生成、事件发布编排，不包含业务规则

### Channel
&ensp;&ensp;每个 `Channel` 都将会被分配一个 `ChannelPipeline` 和 `ChannelConfig`。`ChannelConfig`
包含了该 `Channel` 的所有配置设置，并支持热跟新。由于特定的传输可能具有独特的设置，所以它可能会出现一个 `ChannelConfig`
的子类型。

&ensp;&ensp;由于 `Channel` 是独一无二的，所以为了保证顺序将 `Channel` 声明为 java.lang.Comparable 的一个子接口。
因此，如果两个不同的 `Channel` 实例都返回了想同的散列吗，那么 `AbstractChannel` 中的 `compareTo()` 方法的实现将会抛出一个
`Error`。
## rabbitmq学习笔记：
1、xshell上传文件到linux:  
> rz -y  

2、安装rmp程序：
>rpm -ivh 文件  

3、centos7安装tcp_warpper
>yum -y install tcp_wrappers  
  
4、Exchange的类型：
- direct : 直连方式，两边的```routingKey表示路由规则```需相同
- topic:路由到routingKey指定的Queue中，可以模糊匹配。#代表一个或者多个，*代表一个
- fanout:不走routingKey，只需要简单的将queue绑定在交换机上。  

5、所有的连接信道都是基于Channel的。  
6、confirm做消息的确认，比如消费端消费了，就会给一个确认机制给producer.  
7、return做消息的不可达的监听,比如routingKey设置错误等。  
8、死信队列(Dead-Letter-Exchange)：利用DLX,当消息在队列中变为死信之后，它就会被重新publish到另一个Exchange,这个Exchange就是DLX.以下几种情况会被加入DLX:
- 消息被拒绝(basic.reject,basic.nack),并且requeue=false;
- 消息TTL过期
- 队列达到最大长度。  

9、


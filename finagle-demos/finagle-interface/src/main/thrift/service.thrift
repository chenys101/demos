namespace java io.edr.finagle.demo.service

include "inout.thrift"
include "common.thrift"

/* Hello 服务 */
service HelloService {  
  /* hello方法 */
  inout.HelloResponse hello(1:inout.HelloRequest req);
}
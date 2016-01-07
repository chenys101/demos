namespace java io.edr.finagle.demo.inout

include "common.thrift"
 
struct HelloRequest{
1:common.BaseRequest baseReq,
2:string name
}

struct HelloResponse{
1:common.BaseResponse baseResp,
2:string value
}
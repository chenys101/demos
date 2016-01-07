namespace java io.edr.finagle.demo.inout.common

/*基本返回参数*/
struct BaseResponse{
1:string seqNo,
}

/*基本请求参数*/
struct BaseRequest{
1:string seqNo
}

struct BoolResponse{
1:BaseResponse baseResp,
2:bool value
}

struct I64Response{
1:BaseResponse baseResp,
2:i64 value
}

struct I32Response{
1:BaseResponse baseResp,
2:i32 value
}

struct StringResponse{
1:BaseResponse baseResp,
2:string value
}

struct VoidResponse{
1:BaseResponse baseResp
}
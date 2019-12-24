package com.hc.common.utils;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/3/11 18:20
 * @description：Http响应状态码
 * @modified By：
 * @version:
 */
public interface HttpResponseStatusUtil {

    int SC_CONTINUE = 100; // 请求者应当继续提出请求。 服务器返回此代码表示已收到请求的第一部分，正在等待其余部分。

    int SC_SWITCHING_PROTOCOLS = 101; //请求者已要求服务器切换协议，服务器已确认并准备切换。

    int SC_OK = 200;  //服务器已成功处理了请求。 通常，这表示服务器提供了请求的网页。

    int SC_CREATED = 201; //请求成功并且服务器创建了新的资源。

    int SC_ACCEPTED = 202; //  服务器已接受请求，但尚未处理。

    int SC_NON_AUTHORITATIVE_INFORMATION = 203; //服务器已成功处理了请求，但返回的信息可能来自另一来源。

    int SC_NO_CONTENT = 204; //服务器成功处理了请求，但没有返回任何内容。

    int SC_RESET_CONTENT = 205; // 服务器成功处理了请求，但没有返回任何内容

    int SC_PARTIAL_CONTENT = 206; //服务器成功处理了部分 GET 请求。

    int SC_MULTIPLE_CHOICES = 300; //针对请求，服务器可执行多种操作。 服务器可根据请求者 (user agent) 选择一项操作，或提供操作列表供请求者选择。

    int SC_MOVED_PERMANENTLY = 301; //请求的网页已永久移动到新位置。 服务器返回此响应（对 GET 或 HEAD 请求的响应）时，会自动将请求者转到新位置。

    int SC_MOVED_TEMPORARILY = 302; //服务器目前从不同位置的网页响应请求，但请求者应继续使用原有位置来进行以后的请求。

    int SC_SEE_OTHER = 303; //请求者应当对不同的位置使用单独的 GET 请求来检索响应时，服务器返回此代码。

    int SC_NOT_MODIFIED = 304; //自从上次请求后，请求的网页未修改过。 服务器返回此响应时，不会返回网页内容。

    int SC_USE_PROXY = 305; //请求者只能使用代理访问请求的网页。 如果服务器返回此响应，还表示请求者应使用代理。

    int SC_TEMPORARY_REDIRECT = 307; //服务器目前从不同位置的网页响应请求，但请求者应继续使用原有位置来进行以后的请求。

    int SC_BAD_REQUEST = 400; //服务器不理解请求的语法。

    int SC_UNAUTHORIZED = 401; //请求要求身份验证。对于需要登录的网页，服务器可能返回此响应。

    int SC_FORBIDDEN = 403; //服务器拒绝请求。

    int SC_NOT_FOUND = 404; //服务器找不到请求的网页。

    int SC_METHOD_NOT_ALLOWED = 405; //禁用请求中指定的方法。

    int SC_NOT_ACCEPTABLE = 406; //无法使用请求的内容特性响应请求的网页。

    int SC_PROXY_AUTHENTICATION_REQUIRED = 407; //此状态代码与 401（未授权）类似，但指定请求者应当授权使用代理。

    int SC_REQUEST_TIMEOUT = 408; //服务器等候请求时发生超时。

    int SC_CONFLICT = 409; // 服务器在完成请求时发生冲突。 服务器必须在响应中包含有关冲突的信息

    int SC_GONE = 410; //如果请求的资源已永久删除，服务器就会返回此响应。

    int SC_LENGTH_REQUIRED = 411; // 服务器不接受不含有效内容长度标头字段的请求

    int SC_PRECONDITION_FAILED = 412; //服务器未满足请求者在请求中设置的其中一个前提条件

    int SC_REQUEST_TOO_LONG = 413; //服务器无法处理请求，因为请求实体过大，超出服务器的处理能力。

    int SC_REQUEST_URI_TOO_LONG = 414; //请求的 URI（通常为网址）过长，服务器无法处理。

    int SC_UNSUPPORTED_MEDIA_TYPE = 415; //请求的格式不受请求页面的支持。

    int SC_REQUESTED_RANGE_NOT_SATISFIABLE = 416; //如果页面无法提供请求的范围，则服务器会返回此状态代码。

    int SC_EXPECTATION_FAILED = 417; //服务器未满足”期望”请求标头字段的要求。

    int SC_INTERNAL_SERVER_ERROR = 500; //服务器遇到错误，无法完成请求。

    int SC_NOT_IMPLEMENTED = 501; //服务器不具备完成请求的功能。 例如，服务器无法识别请求方法时可能会返回此代码。

    int SC_BAD_GATEWAY = 502; // 服务器作为网关或代理，从上游服务器收到无效响应。

    int SC_SERVICE_UNAVAILABLE = 503; //服务器目前无法使用（由于超载或停机维护）。 通常，这只是暂时状态。

    int SC_GATEWAY_TIMEOUT = 504; //服务器作为网关或代理，但是没有及时从上游服务器收到请求。

    int SC_HTTP_VERSION_NOT_SUPPORTED = 505; //服务器不支持请求中所用的 HTTP 协议版本。

}

package com.example.springtemplate.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//响应码，1 代表成功; 0 代表失败
    private String message;  //响应信息 描述字符串
    private Object data; //返回的数据

//    public Result(Integer code, String msg, Object data) {
//        this.code = code;
//        this.msg = msg;
//        this.data = data;
//    }

    //增删改 成功响应
    public static Result success(){
        return new Result(0,"success",null);
    }
    //查询 成功响应
    public static Result success(Object data){
        return new Result(0,"success",data);
    }
    //失败响应
    public static Result error(String msg){
        return new Result(1,msg,null);
    }
}

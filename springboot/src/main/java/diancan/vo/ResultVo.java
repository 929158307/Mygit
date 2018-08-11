package diancan.vo;

public class ResultVo<T> {
    //错误码
    private Integer code;
    private String msg;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultVo(){

    }
    public ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ResultVo(T data){
        this.data = data;
    }
}
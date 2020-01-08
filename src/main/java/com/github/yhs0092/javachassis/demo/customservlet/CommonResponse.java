package com.github.yhs0092.javachassis.demo.customservlet;

public class CommonResponse {
  private String msg;

  public CommonResponse() {
  }

  public CommonResponse(String msg) {
    this.msg = msg;
  }

  public String getMsg() {
    return msg;
  }

  public CommonResponse setMsg(String msg) {
    this.msg = msg;
    return this;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("CommonResponse{");
    sb.append("msg='").append(msg).append('\'');
    sb.append('}');
    return sb.toString();
  }
}

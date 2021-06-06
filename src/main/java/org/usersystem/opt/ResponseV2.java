package org.usersystem.opt;

import java.beans.ConstructorProperties;

import org.springframework.http.HttpStatus;

public class ResponseV2 {
    private Integer status;
    private String msg;
    private Result result;

    @ConstructorProperties({"status", "msg", "result"})
    ResponseV2(final Integer status, final String msg, final Result result) {
        this.status = HttpStatus.OK.value();
        this.msg = "请求成功";
        this.status = status;
        this.msg = msg;
        this.result = result;
    }

    public static ResponseV2Builder builder() {
        return new ResponseV2Builder();
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getMsg() {
        return this.msg;
    }

    public Result getResult() {
        return this.result;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public void setResult(final Result result) {
        this.result = result;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ResponseV2)) {
            return false;
        } else {
            ResponseV2 other = (ResponseV2) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47:
                {
                    Object this$status = this.getStatus();
                    Object other$status = other.getStatus();
                    if (this$status == null) {
                        if (other$status == null) {
                            break label47;
                        }
                    } else if (this$status.equals(other$status)) {
                        break label47;
                    }

                    return false;
                }

                Object this$msg = this.getMsg();
                Object other$msg = other.getMsg();
                if (this$msg == null) {
                    if (other$msg != null) {
                        return false;
                    }
                } else if (!this$msg.equals(other$msg)) {
                    return false;
                }

                Object this$result = this.getResult();
                Object other$result = other.getResult();
                if (this$result == null) {
                    if (other$result != null) {
                        return false;
                    }
                } else if (!this$result.equals(other$result)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ResponseV2;
    }


    public String toString() {
        return "ResponseV2(status=" + this.getStatus() + ", msg=" + this.getMsg() + ", result=" + this.getResult() + ")";
    }

    public static class ResponseV2Builder {
        private Integer status;
        private String msg;
        private Result result;

        ResponseV2Builder() {
        }

        public ResponseV2Builder status(final Integer status) {
            this.status = status;
            return this;
        }

        public ResponseV2Builder msg(final String msg) {
            this.msg = msg;
            return this;
        }

        public ResponseV2Builder result(final Result result) {
            this.result = result;
            return this;
        }

        public ResponseV2 build() {
            return new ResponseV2(this.status, this.msg, this.result);
        }

        public String toString() {
            return "ResponseV2.ResponseV2Builder(status=" + this.status + ", msg=" + this.msg + ", result=" + this.result + ")";
        }
    }
}

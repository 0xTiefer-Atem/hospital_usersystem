package org.usersystem.opt;

import java.beans.ConstructorProperties;

public class Result<T> {
    private T data;

    @ConstructorProperties({"data", "extras"})
    Result(final T data, final T extras) {
        this.data = data;
    }

    public static <T> ResultBuilder<T> builder() {
        return new ResultBuilder();
    }

    public T getData() {
        return this.data;
    }


    public void setData(final T data) {
        this.data = data;
    }


    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Result)) {
            return false;
        } else {
            Result<?> other = (Result) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Result;
    }


    public String toString() {
        return "Result(data=" + this.getData();
    }

    public static class ResultBuilder<T> {
        private T data;
        private T extras;

        ResultBuilder() {
        }

        public ResultBuilder<T> data(final T data) {
            this.data = data;
            return this;
        }

        public ResultBuilder<T> extras(final T extras) {
            this.extras = extras;
            return this;
        }

        public Result<T> build() {
            return new Result(this.data, this.extras);
        }

        public String toString() {
            return "Result.ResultBuilder(data=" + this.data + ", extras=" + this.extras + ")";
        }
    }
}

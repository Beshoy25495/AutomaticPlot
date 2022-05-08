package com.bwagih.automaaticirrigateplot.model;

import java.io.Serializable;
import java.util.Date;

public class ResponseModel implements Serializable {
    protected String replyCode;
    protected String replyMessage;
    protected Date requestDate = new Date();

    protected ResponseModel(final ResponseModel.ResponseModelBuilder<?, ?> b) {
        this.replyCode = b.replyCode;
        this.replyMessage = b.replyMessage;
        this.requestDate = b.requestDate;
    }

    public static ResponseModel.ResponseModelBuilder<?, ?> builder() {
        return new ResponseModel.ResponseModelBuilderImpl();
    }

    public void setReplyCode(final String replyCode) {
        this.replyCode = replyCode;
    }

    public void setReplyMessage(final String replyMessage) {
        this.replyMessage = replyMessage;
    }

    public String getReplyCode() {
        return this.replyCode;
    }

    public String getReplyMessage() {
        return this.replyMessage;
    }

    public ResponseModel(final String replyCode, final String replyMessage, final String id, final String idType, final Date requestDate, final String requestStatus, final String requestType, final Long requestId) {
        this.replyCode = replyCode;
        this.requestDate = requestDate;
    }

    public ResponseModel() {
    }

    private static final class ResponseModelBuilderImpl extends ResponseModel.ResponseModelBuilder<ResponseModel, ResponseModel.ResponseModelBuilderImpl> {
        private ResponseModelBuilderImpl() {
        }

        protected ResponseModel.ResponseModelBuilderImpl self() {
            return this;
        }

        public ResponseModel build() {
            return new ResponseModel(this);
        }
    }

    public abstract static class ResponseModelBuilder<C extends ResponseModel, B extends ResponseModel.ResponseModelBuilder<C, B>> {
        private String replyCode;
        private String replyMessage;
        private Date requestDate;

        public ResponseModelBuilder() {
        }

        protected abstract B self();

        public abstract C build();

        public B replyCode(final String replyCode) {
            this.replyCode = replyCode;
            return this.self();
        }

        public B replyMessage(final String replyMessage) {
            this.replyMessage = replyMessage;
            return this.self();
        }

        public B requestDate(final Date requestDate) {
            this.requestDate = requestDate;
            return this.self();
        }

        public String toString() {
            return "ResponseModel.ResponseModelBuilder("
                    + "replyCode=" + this.replyCode
                    + ", replyMessage=" + this.replyMessage
                    + ", requestDate=" + this.requestDate
                    + ")";
        }
    }
}

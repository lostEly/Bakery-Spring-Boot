package com.test.bakery.mail_cfg;

import com.test.bakery.model.Status;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class Feedback {
    @NotNull
    private String to;

    @NotNull
    @Email
    private String body;

    @NotNull
    @Min(10)
    private String topic;

    private Status status;
}

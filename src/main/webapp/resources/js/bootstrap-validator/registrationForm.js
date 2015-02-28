
$(document).ready(function() {
    $('#registerForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: messages["RegisterUserValidator.error.notnull.username"]
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_-]{6,30}$/,
                        message: messages["RegisterUserValidator.error.syntax.username"]
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: messages["RegisterUserValidator.error.notnull.email"]
                    },
                    emailAddress: {
                        message: messages["RegisterUserValidator.error.syntax.email"]
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: messages["RegisterUserValidator.error.notnull.password"]
                    },
                    identical: {
                        field: 'confirmPassword',
                        message: messages["RegisterUserValidator.error.identical.password"]
                    },
                    regexp:{
                        regexp: /((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})/,
                        message: messages["RegisterUserValidator.error.syntax.password"]
                    }

                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: messages["RegisterUserValidator.error.notnull.password"]
                    },
                    identical: {
                        field: 'password',
                        message: messages["RegisterUserValidator.error.identical.password"]
                    },
                    regexp:{
                        regexp: /((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})/,
                        message: messages["RegisterUserValidator.error.syntax.password"]
                    }
                }
            }
        }
    });
});
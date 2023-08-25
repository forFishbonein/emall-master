//发送邮件
function sendEmailApi(data) {
    return axios({
        'url': '/user/sendEmail',
        'method': 'post',
        data
    })
}

//验证码校验
function codeCheckApi(data) {
    return axios({
        'url': '/user/codeCheck',
        'method': 'post',
        data
    })
}

//密码校验
function passwordCheckApi(data) {
    return axios({
        'url': '/user/passwordCheck',
        'method': 'post',
        data
    })
}

//是否注册校验
function isRegisteredApi(data) {
    return axios({
        'url': '/user/isRegistered',
        'method': 'post',
        data
    })
}

//注册
function registerApi(data) {
    return axios({
        'url': '/user/register',
        'method': 'post',
        data
    })
}

//验证码登录
function codeLoginApi(data) {
    return axios({
        'url': '/user/codeLogin',
        'method': 'post',
        data
    })
}

//密码邮件
function passwordLoginApi(data) {
    return axios({
        'url': '/user/passwordLogin',
        'method': 'post',
        data
    })
}

function getUser(data) {
    return axios({
        'url': '/user/all',
        'method': 'get',
        data
    })
}
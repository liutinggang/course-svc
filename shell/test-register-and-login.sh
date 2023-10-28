#!/bin/bash

# 用户注册登录测试脚本

# 普通用户注册测试
function test_register {
    register_url="http://18.142.149.25/course-login-api/api/v1/account/register"
    register_data='{"username": "lisi","smsCode": "123456","password": "123456","mobile": "15232525152","realName": "lisi","identityCode": "1201903921xxxx921039","avatarId": "1717140344096219137"}'
    response=$(curl -s -X PUT -H "Content-Type: application/json; charset=UTF-8" -d "$register_data" "$register_url")
    echo response is $response
    data=$(echo "$response" | jq -r '.data')
    echo "Register Success $data"
}

# 登录测试
function test_login {
    login_url="http://18.142.149.25/course-login-api/api/v1/account/login"
    login_data='{"username": "lisi", "password": "123456"}'
    response=$(curl -s -X POST -H "Content-Type: application/json" -d "$login_data" "$login_url")
    echo $response
}


# 执行测试
test_login




#!/bin/bash

# 发布课程测试
# 登录测试
function test_login {
    login_url="http://18.142.149.25/course-login-api/api/v1/account/login"
    login_data='{"username": "admin", "password": "123456"}'
    response=$(curl -s -X POST -H "Content-Type: application/json" -d "$login_data" "$login_url")
    echo $response
    token=$(echo "$response" | jq -r '.data.token')
    if [ -z "$token" ]; then
        echo "Login failed"
        exit 1
    fi
    echo "Login successful. Token: $token"
    echo "$token" > token.txt
}

# 发布课程测试
function test_publish_course {
    token=$(cat token.txt)
    publish_url="http://18.142.149.25/course-admin-api/api/v1/admin/course/publish"
    headers="Authorization: $token"
    course_data='{"courseName":"test-course-one","price":0,"priceType":0,"fileIds":["1717140344096219137"]}'
    response=$(curl -s -X PUT -H "Content-Type: application/json" -H "$headers" -d "$course_data" "$publish_url")
    echo aa $response
    course_id=$(echo "$response" | jq -r '.data.id')
    if [ -z "$course_id" ]; then
        echo "Failed to publish course"
        exit 1
    fi
    echo "Course published with ID: $course_id"
}

# 执行测试
test_login

test_publish_course
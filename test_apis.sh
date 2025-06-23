#!/bin/bash

# 考试系统API测试脚本
BASE_URL="http://localhost:8080/exam-system"

echo "==================================="
echo "考试系统API功能测试"
echo "==================================="

echo ""
echo "1. 测试获取所有题目"
curl -s -X GET "$BASE_URL/api/questions" | jq '.[0] | {id, content, questionType, defaultScore}'

echo ""
echo "2. 测试获取所有试卷"
curl -s -X GET "$BASE_URL/api/exampapers" | jq '.[0] | {id, title, description}'

echo ""
echo "3. 测试获取所有考试"
curl -s -X GET "$BASE_URL/api/exams" | jq '.[0] | {id, title, examPaperId, startTime, endTime}'

echo ""
echo "4. 测试根据考试ID获取答题卡"
curl -s -X GET "$BASE_URL/api/answer-sheets/exam/1" | jq '.[0] | {id, examId, examineeId, score}'

echo ""
echo "5. 测试创建新题目"
NEW_QUESTION=$(curl -s -X POST "$BASE_URL/api/questions" \
  -H "Content-Type: application/json" \
  -d '{
    "content": "测试题目：Java中的final关键字作用是什么？",
    "questionType": "SINGLE_CHOICE", 
    "defaultScore": 1.5,
    "options": [
      {"optionText": "声明常量", "correct": true},
      {"optionText": "声明变量", "correct": false},
      {"optionText": "声明方法", "correct": false}
    ]
  }')

echo "$NEW_QUESTION" | jq '{id, content, questionType, defaultScore}'

echo ""
echo "6. 测试获取特定题目"
QUESTION_ID=$(echo "$NEW_QUESTION" | jq -r '.id')
curl -s -X GET "$BASE_URL/api/questions/$QUESTION_ID" | jq '{id, content, questionType, options: .options | length}'

echo ""
echo "7. 测试创建新试卷"
NEW_PAPER=$(curl -s -X POST "$BASE_URL/api/exampapers" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "测试试卷",
    "description": "这是一个测试试卷"
  }')

echo "$NEW_PAPER" | jq '{id, title, description}'

echo ""
echo "8. 测试向试卷添加题目"
PAPER_ID=$(echo "$NEW_PAPER" | jq -r '.id')
curl -s -X POST "$BASE_URL/api/exampapers/$PAPER_ID/questions/$QUESTION_ID"
echo "题目已添加到试卷"

echo ""
echo "9. 测试获取试卷的题目"
curl -s -X GET "$BASE_URL/api/exampapers/$PAPER_ID/questions" | jq '.[0] | {id, content, questionType}'

echo ""
echo "==================================="
echo "所有API测试完成！"
echo "==================================="

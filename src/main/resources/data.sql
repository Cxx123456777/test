-- 考试系统测试数据

-- 插入测试题目
INSERT INTO question (content, question_type, default_score) VALUES
('Java中哪个关键字用于定义常量？', 'SINGLE_CHOICE', 2.0),
('以下哪些是Java的基本数据类型？', 'MULTIPLE_CHOICE', 3.0),
('Java是面向对象的编程语言。', 'TRUE_FALSE', 1.0),
('请填写Java中用于输出的方法名：System.out.______', 'FILL_IN_THE_BLANK', 2.0),
('Spring Boot的主要优势是什么？', 'SINGLE_CHOICE', 2.0);

-- 插入题目选项
-- 题目1的选项
INSERT INTO question_option (question_id, option_text, is_correct) VALUES
(1, 'final', true),
(1, 'static', false),
(1, 'const', false),
(1, 'var', false);

-- 题目2的选项
INSERT INTO question_option (question_id, option_text, is_correct) VALUES
(2, 'int', true),
(2, 'String', false),
(2, 'boolean', true),
(2, 'double', true);

-- 题目3的选项
INSERT INTO question_option (question_id, option_text, is_correct) VALUES
(3, '正确', true),
(3, '错误', false);

-- 题目4的选项（填空题的正确答案）
INSERT INTO question_option (question_id, option_text, is_correct) VALUES
(4, 'println', true),
(4, 'print', true);

-- 题目5的选项
INSERT INTO question_option (question_id, option_text, is_correct) VALUES
(5, '简化配置', true),
(5, '提高性能', false),
(5, '减少代码量', false),
(5, '自动装配', false);

-- 插入测试试卷
INSERT INTO exam_paper (title, description) VALUES
('Java基础知识测试', '测试Java基础语法和概念的掌握程度'),
('Spring Boot入门测试', '测试Spring Boot框架的基础知识');

-- 将题目添加到试卷
INSERT INTO exampaper_questions (exampaper_id, question_id, question_order) VALUES
(1, 1, 1),
(1, 2, 2),
(1, 3, 3),
(1, 4, 4),
(2, 5, 1);

-- 插入测试考试
INSERT INTO exam (title, exampaper_id, start_time, end_time) VALUES
('Java基础考试', 1, '2024-01-01 09:00:00', '2024-01-01 11:00:00'),
('Spring Boot考试', 2, '2024-01-02 14:00:00', '2024-01-02 15:30:00');

-- 插入考试考生
INSERT INTO exam_examinees (exam_id, examinee_id) VALUES
(1, 'student001'),
(1, 'student002'),
(1, 'student003'),
(2, 'student001'),
(2, 'student004');

-- 插入测试答题卡
INSERT INTO answer_sheet (exam_id, examinee_id, score, submission_time) VALUES
(1, 'student001', 8.0, '2024-01-01 10:30:00'),
(1, 'student002', 6.0, '2024-01-01 10:45:00');

-- 插入测试答案
-- student001的答案
INSERT INTO answer (answer_sheet_id, question_id, selected_option_id, is_correct, score) VALUES
(1, 1, 1, true, 2.0),   -- 选择了final，正确
(1, 3, 5, true, 1.0);   -- 判断题选择正确

INSERT INTO answer (answer_sheet_id, question_id, selected_option_ids, is_correct, score) VALUES
(1, 2, '2,4,6', true, 3.0);  -- 多选题选择了int,boolean,double，正确

INSERT INTO answer (answer_sheet_id, question_id, answer_text, is_correct, score) VALUES
(1, 4, 'println', true, 2.0);  -- 填空题答案正确

-- student002的答案
INSERT INTO answer (answer_sheet_id, question_id, selected_option_id, is_correct, score) VALUES
(2, 1, 2, false, 0.0),  -- 选择了static，错误
(2, 3, 6, false, 0.0);  -- 判断题选择错误

INSERT INTO answer (answer_sheet_id, question_id, selected_option_ids, is_correct, score) VALUES
(2, 2, '2,4', false, 0.0);  -- 多选题只选择了int,boolean，不完整

INSERT INTO answer (answer_sheet_id, question_id, answer_text, is_correct, score) VALUES
(2, 4, 'print', true, 2.0);  -- 填空题答案正确

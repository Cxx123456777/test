-- 考试系统数据库初始化脚本 (H2数据库兼容版本)

-- 题目表
CREATE TABLE IF NOT EXISTS question (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '题目ID',
    content TEXT NOT NULL COMMENT '题目内容',
    question_type VARCHAR(50) NOT NULL COMMENT '题目类型',
    default_score DECIMAL(5,2) NOT NULL DEFAULT 0.00 COMMENT '默认分值',
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 题目选项表
CREATE TABLE IF NOT EXISTS question_option (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '选项ID',
    question_id BIGINT NOT NULL COMMENT '题目ID',
    option_text TEXT NOT NULL COMMENT '选项内容',
    is_correct BOOLEAN NOT NULL DEFAULT FALSE COMMENT '是否为正确答案',
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE
);

-- 试卷表
CREATE TABLE IF NOT EXISTS exam_paper (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '试卷ID',
    title VARCHAR(255) NOT NULL COMMENT '试卷标题',
    description TEXT COMMENT '试卷描述',
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 试卷题目关联表
CREATE TABLE IF NOT EXISTS exampaper_questions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '关联ID',
    exampaper_id BIGINT NOT NULL COMMENT '试卷ID',
    question_id BIGINT NOT NULL COMMENT '题目ID',
    question_order INT DEFAULT 0 COMMENT '题目顺序',
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (exampaper_id) REFERENCES exam_paper(id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE,
    UNIQUE (exampaper_id, question_id)
);

-- 考试表
CREATE TABLE IF NOT EXISTS exam (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '考试ID',
    title VARCHAR(255) NOT NULL COMMENT '考试标题',
    exampaper_id BIGINT NOT NULL COMMENT '试卷ID',
    start_time TIMESTAMP NOT NULL COMMENT '开始时间',
    end_time TIMESTAMP NOT NULL COMMENT '结束时间',
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (exampaper_id) REFERENCES exam_paper(id)
);

-- 考试考生关联表
CREATE TABLE IF NOT EXISTS exam_examinees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '关联ID',
    exam_id BIGINT NOT NULL COMMENT '考试ID',
    examinee_id VARCHAR(100) NOT NULL COMMENT '考生ID',
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (exam_id) REFERENCES exam(id) ON DELETE CASCADE,
    UNIQUE (exam_id, examinee_id)
);

-- 答题卡表
CREATE TABLE IF NOT EXISTS answer_sheet (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '答题卡ID',
    exam_id BIGINT NOT NULL COMMENT '考试ID',
    examinee_id VARCHAR(100) NOT NULL COMMENT '考生ID',
    score DECIMAL(5,2) DEFAULT 0.00 COMMENT '总分',
    submission_time TIMESTAMP NULL COMMENT '提交时间',
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (exam_id) REFERENCES exam(id),
    UNIQUE (exam_id, examinee_id)
);

-- 答案表
CREATE TABLE IF NOT EXISTS answer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '答案ID',
    answer_sheet_id BIGINT NOT NULL COMMENT '答题卡ID',
    question_id BIGINT NOT NULL COMMENT '题目ID',
    selected_option_id BIGINT NULL COMMENT '选中的选项ID（单选、判断）',
    selected_option_ids TEXT NULL COMMENT '选中的选项IDs（多选，逗号分隔）',
    answer_text TEXT NULL COMMENT '答案文本（填空题）',
    is_correct BOOLEAN DEFAULT FALSE COMMENT '是否正确',
    score DECIMAL(5,2) DEFAULT 0.00 COMMENT '得分',
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (answer_sheet_id) REFERENCES answer_sheet(id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) REFERENCES question(id),
    UNIQUE (answer_sheet_id, question_id)
);

-- 索引会由H2数据库自动创建

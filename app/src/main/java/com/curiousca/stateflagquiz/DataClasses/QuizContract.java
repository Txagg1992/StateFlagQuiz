package com.curiousca.stateflagquiz.DataClasses;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract(){}

    public static class QuestionsTable implements BaseColumns{
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_IMAGE_ID = "image_id";
        public static final String COLUMN_IMAGE_BITMAP = "image_bitmap";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
    }
}

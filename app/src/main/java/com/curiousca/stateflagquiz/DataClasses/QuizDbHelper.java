package com.curiousca.stateflagquiz.DataClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.curiousca.stateflagquiz.DataClasses.QuizContract.QuestionsTable;


public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FlagQuiz.db";
    public static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
    super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_IMAGE_ID + " TEXT, " +
                QuestionsTable.COLUMN_IMAGE_BITMAP + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable(){
        Question q1 = new Question("A is correct",  "A", "B", "C", 1);
        addQuestion(q1);
        Question q2 = new Question("B is correct", "A", "B", "C", 2);
        addQuestion(q2);
        Question q3 = new Question("c is correct", "A", "B", "C", 3);
        addQuestion(q3);
        Question q4 = new Question("A is correct again", "A", "B", "C", 1);
        addQuestion(q4);
        Question q5 = new Question("B is correct again", "A", "B", "C", 2);
        addQuestion(q5);
    }

    private void addQuestion(Question question){
        ContentValues contentValues = new ContentValues();
        contentValues.put(QuestionsTable.COLUMN_IMAGE_ID, question.getImageId());
        contentValues.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        contentValues.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        contentValues.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        contentValues.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, contentValues);

    }

    public List<Question> getAllQueations(){
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (cursor.moveToFirst()){
            do {
                Question question = new Question();
                question.setImageId(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_IMAGE_ID)));
                question.setImageByteArray(cursor.getBlob(cursor.getColumnIndex(QuestionsTable.COLUMN_IMAGE_BITMAP)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);

            }while (cursor.moveToNext());
        }

//        ImageHelper imageHelper = new ImageHelper();
//
//        if (cursor.moveToFirst()){
//            do {
//                imageHelper.setImageId(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_IMAGE_ID)));
//                imageHelper.setImageByteArray(cursor.getBlob(cursor.getColumnIndex(QuestionsTable.COLUMN_IMAGE_BITMAP)));
//            }while (cursor.moveToNext());
//        }

        cursor.close();
        return questionList;
    }

//    private void insetImage(Drawable dbDrawable, String imageId){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(QuestionsTable.COLUMN_IMAGE_ID, imageId);
//        Bitmap bitmap = ((BitmapDrawable)dbDrawable).getBitmap();
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        values.put(QuestionsTable.COLUMN_IMAGE_BITMAP, stream.toByteArray());
//        db.insert(QuestionsTable.TABLE_NAME, null, values);
//        db.close();
//    }
}

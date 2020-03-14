package cc.mrbird.febs.timetable.entity;

public enum TimetableAddState {
    SAME_CLASS_SAME_TIME_CLASH,
    SAME_TEACHER_SAME_TIME_CLASH,
    CLASSROOM_PEOPLE_AMOUNT_CLASH,
    SAME_CLASSROOM_SAME_TIME_CLASH,
    COURSE_PERIOD_CLASH,
    SUCCESS
}

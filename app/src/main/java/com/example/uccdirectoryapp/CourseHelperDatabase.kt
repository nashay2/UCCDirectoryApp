package com.example.uccdirectoryapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.uccdirectoryapp.models.Course

class CourseHelperDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "ucc_courses.db"
        private const val DATABASE_VERSION = 3

        private const val TABLE_COURSES = "courses"
        private const val COLUMN_ID = "id"
        private const val COLUMN_CODE = "code"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_CREDITS = "credits"
        private const val COLUMN_PREREQUISITES = "prerequisites"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_DEPARTMENT = "department"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_COURSES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_CODE TEXT,
                $COLUMN_NAME TEXT,
                $COLUMN_CREDITS INTEGER,
                $COLUMN_PREREQUISITES TEXT,
                $COLUMN_DESCRIPTION TEXT,
                $COLUMN_DEPARTMENT TEXT
            );
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_COURSES")
        onCreate(db)
    }


    fun addCourse(course: Course): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_CODE, course.code)
            put(COLUMN_NAME, course.name)
            put(COLUMN_CREDITS, course.credits)
            put(COLUMN_PREREQUISITES, course.preRequisites)
            put(COLUMN_DESCRIPTION, course.description)
            put(COLUMN_DEPARTMENT, course.department)
        }
        return db.insert(TABLE_COURSES, null, values)
    }

    fun getAllCourses(): List<Course> {
        val courseList = mutableListOf<Course>()
        val db = readableDatabase
        val cursor = db.query(TABLE_COURSES, null, null, null, null, null, null)

        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(COLUMN_ID))
                val code = getString(getColumnIndexOrThrow(COLUMN_CODE))
                val name = getString(getColumnIndexOrThrow(COLUMN_NAME))
                val credits = getInt(getColumnIndexOrThrow(COLUMN_CREDITS))
                val prereq = getString(getColumnIndexOrThrow(COLUMN_PREREQUISITES))
                val description = getString(getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                val department = getString(getColumnIndexOrThrow(COLUMN_DEPARTMENT))
                courseList.add(Course(code, name, credits, prereq, description, department, id))
            }
            close()
        }

        return courseList
    }

    fun populateTestCourses() {
        if (getAllCourses().isEmpty()) {
            // School of Technology
            addCourse(Course("CSAI101", "Intro to AI", 3, "None", "AI fundamentals: ML, NLP, Vision.","School of Technology"))
            addCourse(Course("CS201", "Data Structures", 3, "Intro to CS", "Stacks, queues, trees.","School of Technology"))
            addCourse(Course("CS301", "Operating Systems", 3, "CS201", "Processes, threads, memory.","School of Technology"))
            addCourse(Course("CS401", "Mobile Dev", 3, "CS201", "Build Android/iOS apps.","School of Technology"))
            addCourse(Course("CS410", "Cybersecurity", 3, "CS301", "Threats, encryption, security.","School of Technology"))
            addCourse(Course("CS420", "Cloud Computing", 3, "CS301", "AWS, Azure, DevOps.","School of Technology"))
            addCourse(Course("CS430", "Web Development", 3, "None", "HTML, CSS, JS, backend.","School of Technology"))
            addCourse(Course("CS440", "Databases", 3, "None", "SQL, normalization.","School of Technology"))
            addCourse(Course("CS450", "Networking", 3, "CS201", "TCP/IP, routing, protocols.","School of Technology"))
            addCourse(Course("CS460", "AI Capstone", 3, "CSAI101", "Project using AI.","School of Technology"))

            // School of Business
            addCourse(Course("BUS101", "Intro to Business", 3, "None", "Basics of business operations.","School of Business"))
            addCourse(Course("MKTG101", "Marketing Principles", 3, "None", "Marketing strategies.","School of Business"))
            addCourse(Course("BUS201", "Business Law", 3, "BUS101", "Legal aspects in business.","School of Business"))
            addCourse(Course("FIN301", "Finance", 3, "BUS101", "Financial planning and budgeting.","School of Business"))
            addCourse(Course("ACCT301", "Accounting", 3, "None", "Balance sheet, ledger, reporting.","School of Business"))
            addCourse(Course("BUS310", "E-Commerce", 3, "MKTG101", "Online commerce and platforms.","School of Business"))
            addCourse(Course("HRM201", "HR Management", 3, "None", "Recruitment, training, retention.","School of Business"))
            addCourse(Course("CRM204", "CRM", 3, "MKTG101", "Customer relationship strategies.","School of Business"))
            addCourse(Course("ENTR401", "Entrepreneurship", 3, "BUS101", "Startup development.","School of Business"))
            addCourse(Course("BUS499", "Business Capstone", 3, "All above", "Integrative business project.","School of Business"))

            // School of Education
            addCourse(Course("EDU101", "Intro to Education", 3, "None", "Philosophy and history of education.","School of Education"))
            addCourse(Course("EDU201", "Curriculum Theory", 3, "EDU101", "Curriculum design and evaluation.","School of Education"))
            addCourse(Course("EDU301", "Educational Psychology", 3, "None", "Psych principles in teaching.","School of Education"))
            addCourse(Course("EDU310", "Instructional Design", 3, "EDU201", "Creating learning environments.","School of Education"))
            addCourse(Course("EDU320", "Classroom Management", 3, "None", "Behavioral and discipline models.","School of Education"))
            addCourse(Course("EDU401", "Assessment Methods", 3, "EDU201", "Evaluating student learning.","School of Education"))
            addCourse(Course("EDU402", "Inclusive Education", 3, "None", "Special needs and accessibility.","School of Education"))
            addCourse(Course("EDU410", "EdTech Tools", 3, "EDU310", "Educational software and platforms.","School of Education"))
            addCourse(Course("EDU450", "Student Teaching", 3, "All above", "Real classroom teaching practicum.","School of Education"))
            addCourse(Course("EDU499", "Capstone in Education", 3, "EDU450", "Research-based education project.","School of Education"))

            // School of Nursing
            addCourse(Course("NUR101", "Intro to Nursing", 3, "None", "Overview of the nursing profession.","School of Nursing"))
            addCourse(Course("NUR201", "Health Assessment", 3, "NUR101", "Patient evaluation techniques.","School of Nursing"))
            addCourse(Course("NUR210", "Pharmacology", 3, "NUR101", "Medications and effects.","School of Nursing"))
            addCourse(Course("NUR220", "Clinical Practice I", 3, "NUR101", "Hands-on patient care.","School of Nursing"))
            addCourse(Course("NUR301", "Medical-Surgical Nursing", 3, "NUR201", "Hospital-based procedures.","School of Nursing"))
            addCourse(Course("NUR310", "Pediatric Nursing", 3, "NUR301", "Child-focused care.","School of Nursing"))
            addCourse(Course("NUR320", "Mental Health Nursing", 3, "NUR301", "Psychiatric support.","School of Nursing"))
            addCourse(Course("NUR330", "Community Health", 3, "NUR301", "Public and rural healthcare.","School of Nursing"))
            addCourse(Course("NUR401", "Leadership in Nursing", 3, "NUR301", "Ethics, law, management.","School of Nursing"))
            addCourse(Course("NUR499", "Nursing Capstone", 3, "All above", "Final nursing portfolio.","School of Nursing"))

            // School of Engineering
            addCourse(Course("ENGR101", "Intro to Engineering", 3, "None", "Engineering disciplines overview.","School of Engineering"))
            addCourse(Course("ENGR201", "Statics", 3, "Physics I", "Forces in equilibrium.","School of Engineering"))
            addCourse(Course("ENGR202", "Dynamics", 3, "ENGR201", "Motion of bodies.","School of Engineering"))
            addCourse(Course("ENGR210", "Circuits", 3, "Physics II", "Electricity and electronics.","School of Engineering"))
            addCourse(Course("ENGR220", "Materials Science", 3, "Chemistry", "Engineering materials.","School of Engineering"))
            addCourse(Course("ENGR301", "Fluid Mechanics", 3, "ENGR201", "Flow principles.","School of Engineering"))
            addCourse(Course("ENGR302", "Thermodynamics", 3, "Physics II", "Energy systems.","School of Engineering"))
            addCourse(Course("ENGR310", "Control Systems", 3, "ENGR210", "Feedback and regulation.","School of Engineering"))
            addCourse(Course("ENGR401", "Engineering Design", 3, "All above", "Real-world design project.","School of Engineering"))
            addCourse(Course("ENGR499", "Engineering Capstone", 3, "ENGR401", "Team project and report.","School of Engineering"))

            // School of Arts
            addCourse(Course("ART101", "Intro to Visual Arts", 3, "None", "History and principles of visual expression.","School of Arts"))
            addCourse(Course("ART110", "Drawing Fundamentals", 3, "ART101", "Techniques in pencil, ink, and charcoal.","School of Arts"))
            addCourse(Course("ART201", "Painting Techniques", 3, "ART110", "Watercolor, oil, acrylic skills.","School of Arts"))
            addCourse(Course("ART210", "Art History", 3, "None", "From classical to modern art movements.","School of Arts"))
            addCourse(Course("ART220", "Sculpture Basics", 3, "ART101", "Clay, wood, and 3D form crafting.","School of Arts"))
            addCourse(Course("ART301", "Digital Illustration", 3, "ART110", "Drawing with digital tools.","School of Arts"))
            addCourse(Course("ART310", "Photography", 3, "None", "Lighting, composition, editing.","School of Arts"))
            addCourse(Course("ART320", "Portfolio Development", 3, "ART201", "Build a professional art portfolio.","School of Arts"))
            addCourse(Course("ART401", "Contemporary Art", 3, "ART210", "Current global art practices.","School of Arts"))
            addCourse(Course("ART499", "Senior Art Project", 3, "All above", "Capstone gallery project.","School of Arts"))

            // School of Agriculture
            addCourse(Course("AG101", "Intro to Agriculture", 3, "None", "Fundamentals of farming and crops.","School of Agriculture"))
            addCourse(Course("AG201", "Soil Science", 3, "AG101", "Soil types, testing, fertilization.","School of Agriculture"))
            addCourse(Course("AG210", "Crop Production", 3, "AG101", "Vegetables, grains, rotations.","School of Agriculture"))
            addCourse(Course("AG220", "Livestock Management", 3, "AG101", "Cattle, poultry, health care.","School of Agriculture"))
            addCourse(Course("AG301", "Agribusiness", 3, "AG101", "Farming as a business.","School of Agriculture"))
            addCourse(Course("AG310", "Irrigation Techniques", 3, "AG201", "Water use in agriculture.","School of Agriculture"))
            addCourse(Course("AG320", "Plant Pathology", 3, "AG210", "Pest/disease prevention.","School of Agriculture"))
            addCourse(Course("AG401", "Farm Machinery", 3, "AG210", "Equipment operations.","School of Agriculture"))
            addCourse(Course("AG410", "Organic Farming", 3, "AG301", "Sustainable ag methods.","School of Agriculture"))
            addCourse(Course("AG499", "Agri Capstone Project", 3, "All above", "Research-based farm plan.","School of Agriculture"))

            // School of Law
            addCourse(Course("LAW101", "Intro to Law", 3, "None", "Legal systems, rights, and duties.","School of Law"))
            addCourse(Course("LAW201", "Contract Law", 3, "LAW101", "Contracts, obligations, enforcement.","School of Law"))
            addCourse(Course("LAW210", "Tort Law", 3, "LAW101", "Negligence, liability, compensation.","School of Law"))
            addCourse(Course("LAW220", "Constitutional Law", 3, "LAW101", "Government and rights.","School of Law"))
            addCourse(Course("LAW301", "Criminal Law", 3, "LAW101", "Crimes, penalties, defenses.","School of Law"))
            addCourse(Course("LAW310", "Family Law", 3, "LAW101", "Marriage, custody, divorce.","School of Law"))
            addCourse(Course("LAW320", "Property Law", 3, "LAW101", "Ownership, real estate, land use.","School of Law"))
            addCourse(Course("LAW401", "Legal Ethics", 3, "LAW101", "Professional conduct for lawyers.","School of Law"))
            addCourse(Course("LAW410", "Legal Writing", 3, "LAW201", "Briefs, memos, case law.","School of Law"))
            addCourse(Course("LAW499", "Law Capstone", 3, "All above", "Mock trial or legal thesis.","School of Law"))
        }
    }
}


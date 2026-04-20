package com.example.studentregisterdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentregisterdbapp.db.Student
import com.example.studentregisterdbapp.db.StudentDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var saveButtton: Button
    private lateinit var clearButton: Button
    private lateinit var studentRecyclerView: RecyclerView
    private lateinit var adapter: StudentRecyclerViewAdapter
    private lateinit var viewModel: StudentViewModel
    private var isListItemClicked = false

    private lateinit var selectedStudent: Student
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.etName)
        emailEditText = findViewById(R.id.etEmail)
        saveButtton = findViewById(R.id.btnSave)
        clearButton = findViewById(R.id.btnClear)

        studentRecyclerView = findViewById(R.id.rvStudent)
        val dao = StudentDatabase.getInstance(application).studentDao()
        val factory = StudentViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(StudentViewModel::class.java)
        saveButtton.setOnClickListener {
           if(isListItemClicked){
               updateStudentData()
               clearInput()
           }else{
               saveStudentData()
               clearInput()
           }
           
        }
        clearButton.setOnClickListener {
            if(isListItemClicked){
                deletedStudentData()
            }
            else{
                clearInput()
            }
          
        }

        initRecyclerView()
    }

    private fun saveStudentData() {
        /*val name=nameEditText.text.toString()
        val email=emailEditText.text.toString()
        val student=Student(0,name,email)
        viewModel.insertStudent(student)*/
        viewModel.insertStudent(
            Student(
                0,
                nameEditText.text.toString(),
                emailEditText.text.toString()
            )
        )
    }

    private fun updateStudentData() {
        viewModel.updateStudent(
            Student(
                selectedStudent.id,
                nameEditText.text.toString(),
                emailEditText.text.toString()

            )
        )

        saveButtton.text = "Save"
        clearButton.text = "Clear"
        isListItemClicked = false
    }

    private fun deletedStudentData() {
        viewModel.deleteStudent(
            Student(
                selectedStudent.id,
                nameEditText.text.toString(),
                emailEditText.text.toString()

            )
        )

        saveButtton.text = "Save"
        clearButton.text = "Clear"
        isListItemClicked = false
    }

    private fun clearInput() {
        nameEditText.setText("")
        emailEditText.setText("")
    }

    private fun initRecyclerView() {
        studentRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentRecyclerViewAdapter { selectedItem: Student ->
            listItemClicked(selectedItem)
        }
        studentRecyclerView.adapter = adapter

        displayStudentList()
    }

    private fun displayStudentList() {
        viewModel.students.observe(this, {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(student: Student) {
        /*Toast.makeText(
            this,
            "Student name is ${student.name}", Toast.LENGTH_LONG
        ).show()*/
        selectedStudent = student
        saveButtton.text = "Update"
        clearButton.text = "Delete"
        isListItemClicked = true
        nameEditText.setText(selectedStudent.name)
        emailEditText.setText(selectedStudent.email)
    }
}
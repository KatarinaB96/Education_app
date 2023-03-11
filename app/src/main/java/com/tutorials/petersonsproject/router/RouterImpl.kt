package com.tutorials.petersonsproject.router

import androidx.fragment.app.FragmentManager
import com.tutorials.petersonsproject.R
import com.tutorials.petersonsproject.ui.*
import com.tutorials.petersonsproject.ui.FragmentExam.Companion.createInstance
import com.tutorials.petersonsproject.ui.screenmodels.TopicScreenModel


class RouterImpl(private val fragmentManager: FragmentManager) : Router {

    override fun showHomeFragment() {

        val fragmentHome = FragmentHome()

        fragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view, fragmentHome)
//            addToBackStack(null)
            commit()
        }
    }

    override fun showProfileFragment() {
        val fragmentProfile = FragmentProfile()
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view_profile, fragmentProfile)
//            addToBackStack(null)
            commit()
        }
    }

    override fun showExamFragment(id: Long, title: String) {
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view, createInstance(id, title))
            setReorderingAllowed(true)
            addToBackStack(null)
            commit()
        }
    }

    override fun showContent() {
        val fragmentContent = FragmentContent()
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view, fragmentContent)
            addToBackStack(null)
            commit()
        }
    }

    override fun showLesson(
        url: String
    ) {
        fragmentManager.beginTransaction().apply {
            replace(
                R.id.fragment_container_view,
                LessonFragment.createInstance(url)
            )
            setReorderingAllowed(true)
            addToBackStack(null)
            commit()
        }
    }

    override fun showQuiz() {
        val fragmentQuiz = QuizFragment()
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view, fragmentQuiz)
            addToBackStack(null)
            commit()
        }
    }

    override fun showFolder(contents: List<TopicScreenModel>) {
        val fragmentFolder = FolderFragment(contents)
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view, fragmentFolder)
            addToBackStack(null)
            commit()
        }
    }

    override fun routerPopBack() {
        fragmentManager.popBackStack()
    }

}
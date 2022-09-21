package com.example.surveyemojisapp

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils

class AnimationButton(val context : Context) {


    fun move( view: View, animation_resource: Int){

        view.startAnimation(AnimationUtils.loadAnimation(context,animation_resource))


    }



}
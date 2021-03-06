/* Copyright (C) 2016 Tomáš Janoušek
 * This file is a part of locus-rflkt-addon.
 * See the COPYING and LICENSE files in the project root directory.
 */

name := "locus-rflkt-addon"

import android.Keys._
android.Plugin.androidBuild
protifySettings

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")
scalaVersion := "2.11.7"
scalacOptions in Compile ++= Seq("-explaintypes", "-unchecked", "-feature", "-deprecation")

updateCheck in Android := {} // disable update check
proguardCache in Android ++= Seq(
  "org.scaloid",
  "com.google",
  "com.wahoofitness",
  "com.dsi",
  "com.garmin",
  "android.support",
  "locus"
)

proguardOptions in Android ++= Seq("-keepattributes Signature", "-dontwarn org.scaloid.**")
proguardConfig in Android := {
  // This is probably wrong
  // (https://github.com/pfn/android-sdk-plugin/issues/242) but it
  // seems to work fine, and even if it didn't, it doesn't matter as
  // AndroidManifest changes require clean/reload anyway.
  val debug = (apkbuildDebug in Android).value()
  (proguardConfig in Android).value filter {
    case "-dontobfuscate" if !debug => false
    case "-dontoptimize" if !debug => false
    case _ => true
  }
}

libraryDependencies += "org.scaloid" %% "scaloid" % "4.1"
libraryDependencies += "com.android.support" % "support-v4" % "23.1.1"

run <<= run in Android
install <<= install in Android

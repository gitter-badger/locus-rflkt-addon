name := "locus-rflkt-addon"

import android.Keys._
android.Plugin.androidBuild

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")
scalaVersion := "2.11.7"
scalacOptions in Compile ++= Seq("-explaintypes", "-unchecked", "-feature", "-deprecation")

updateCheck in Android := {} // disable update check
proguardCache in Android ++= Seq("org.scaloid")

proguardOptions in Android ++= Seq("-dontobfuscate", "-dontoptimize", "-keepattributes Signature", "-printseeds target/seeds.txt", "-printusage target/usage.txt"
  , "-dontwarn scala.collection.**" // required from Scala 2.11.4
  , "-dontwarn org.scaloid.**" // this can be omitted if current Android Build target is android-16
)

resolvers += "Arcao" at "http://maven.arcao.com/"

libraryDependencies += "org.scaloid" %% "scaloid" % "4.1"
libraryDependencies += "menion" % "locus-api" % "1.37"
libraryDependencies += "menion" % "locus-api-android" % "1.37.41"

run <<= run in Android
install <<= install in Android

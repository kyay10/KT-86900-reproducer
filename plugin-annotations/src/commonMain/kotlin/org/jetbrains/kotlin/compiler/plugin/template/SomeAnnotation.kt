package org.jetbrains.kotlin.compiler.plugin.template

public annotation class SomeAnnotation

public interface Base1 {
  public fun foo(nothing: Nothing)
}

public interface Base: Base1 {
  override fun foo(nothing: Nothing) {}
}
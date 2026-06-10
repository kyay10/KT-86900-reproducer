import org.jetbrains.kotlin.compiler.plugin.template.*

@SomeAnnotation fun interface Foo: Base
// generates `fun foo() {}`

fun makeFoo(foo: Foo) {}

fun box(): String {
  makeFoo { } // compiler now considers functions named `foo` when searching for the SAM, so it works!
  return "OK"
}
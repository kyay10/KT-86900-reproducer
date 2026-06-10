import org.jetbrains.kotlin.compiler.plugin.template.*

@SomeAnnotation fun interface Foo
// generates `fun foo() {}`

fun makeFoo(foo: Foo) {}

fun box(): String {
  makeFoo { } // ARGUMENT_TYPE_MISMATCH: Argument type mismatch: actual type is '() -> Unit', but 'Foo' was expected
  return "OK"
}
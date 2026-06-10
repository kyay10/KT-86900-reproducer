package org.jetbrains.kotlin.compiler.plugin.template.fir

import org.jetbrains.kotlin.GeneratedDeclarationKey
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.hasAnnotation
import org.jetbrains.kotlin.fir.extensions.FirDeclarationGenerationExtension
import org.jetbrains.kotlin.fir.extensions.MemberGenerationContext
import org.jetbrains.kotlin.fir.plugin.createMemberFunction
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

val FOO_NAME = Name.identifier("foo")

val PACKAGE_FQN = FqName("org.jetbrains.kotlin.compiler.plugin.template")
val SOME_ANNOTATION_CLASS_ID = ClassId(PACKAGE_FQN, Name.identifier("SomeAnnotation"))

class SimpleClassGenerator(session: FirSession) : FirDeclarationGenerationExtension(session) {
    override fun generateFunctions(
        callableId: CallableId,
        context: MemberGenerationContext?
    ): List<FirNamedFunctionSymbol> {
        val owner = context?.owner ?: return emptyList()
        val function = createMemberFunction(owner, Key, callableId.callableName, returnType = session.builtinTypes.unitType.coneType) {
            modality = Modality.ABSTRACT
        }
        return listOf(function.symbol)
    }

    override fun getCallableNamesForClass(classSymbol: FirClassSymbol<*>, context: MemberGenerationContext) =
      if (classSymbol.hasAnnotation(SOME_ANNOTATION_CLASS_ID, session)) setOf(FOO_NAME) else emptySet()

    object Key : GeneratedDeclarationKey()
}

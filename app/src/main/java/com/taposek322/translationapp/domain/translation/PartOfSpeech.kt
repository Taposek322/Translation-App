package com.taposek322.translationapp.domain.translation

enum class PartOfSpeech(val part:String) {
    N("Noun"),
    V("Verb"),
    J("Adjective"),
    R("Adverb"),
    Prp("Preposition"),
    Prn("Pronoun"),
    Crd("Cardinal number"),
    Cjc("Conjunction"),
    Exc("Interjection"),
    Det("Article"),
    Abb("Abbreviation"),
    X("Particle"),
    Ord("Ordinal number"),
    Md("Modal verb"),
    Ph("Phrase"),
    Phi("Idiom"),
    Unknown("Unknown part of speech")
}

fun toPartOfSpeech(pos:String): PartOfSpeech{
    return when(pos){
        "n" -> PartOfSpeech.N
        "v" -> PartOfSpeech.V
        "j" -> PartOfSpeech.J
        "r" -> PartOfSpeech.R
        "prp" -> PartOfSpeech.Prp
        "prn" -> PartOfSpeech.Prn
        "crd" -> PartOfSpeech.Crd
        "cjc" -> PartOfSpeech.Cjc
        "exc" -> PartOfSpeech.Exc
        "det" -> PartOfSpeech.Det
        "abb" -> PartOfSpeech.Abb
        "x" -> PartOfSpeech.X
        "ord" -> PartOfSpeech.Ord
        "md" -> PartOfSpeech.Md
        "ph" -> PartOfSpeech.Ph
        "phi" -> PartOfSpeech.Phi
        else -> PartOfSpeech.Unknown
    }
}
package com.nedink.lang;

public enum Phoneme {

    A("a", Phoneme.B),
    E("e", Phoneme.B),
    I("i", Phoneme.B),
    O("o", Phoneme.B),
    U("u", Phoneme.B),

    B("b"),
    C("c"),
    D("d"),
    F("f"),
    G("g"),
    H("h"),
    J("j"),
    K("k"),
    L("l"),
    M("m"),
    N("n"),
    P("p"),
    Q("q"),
    R("r"),
    S("s"),
    T("t"),
    V("v"),
    W("w"),
    X("x"),
    Y("y"),
    Z("z")
    ;

    Phoneme(String string, Phoneme... connectors) {

    }
}

package com.nedink.lang;

public enum Phoneme {

    I("i"),
    E("e"),
    A("a"),
    O("o"),
    U("u"),

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
    R("r"),
    S("s"),
    T("t"),
    V("v"),
    W("w"),
    X("x"),
    Y("y"),
    Z("z")
    ;

    static {
        I.connectors = new Phoneme[]{null, B, C, D, F, G, H, J, K, L, M, N, P, R, S, T, V, W, X, Y, Z};
        E.connectors = new Phoneme[]{null, B, C, D, F, G, H, J, K, L, M, N, P, R, S, T, V, W, X, Y, Z};
        A.connectors = new Phoneme[]{null, B, C, D, F, G, H, J, K, L, M, N, P, R, S, T, V, W, X, Y, Z};
        O.connectors = new Phoneme[]{null, B, C, D, F, G, H, J, K, L, M, N, P, R, S, T, V, W, X, Y, Z};
        U.connectors = new Phoneme[]{null, B, C, D, F, G, H, J, K, L, M, N, P, R, S, T, V, W, X, Y, Z};

        B.connectors = new Phoneme[]{null, I, E, A, O, U};
        C.connectors = new Phoneme[]{null, I, E, A, O, U, H, L, R, T};
        D.connectors = new Phoneme[]{null, I, E, A, O, U, R, W};
        F.connectors = new Phoneme[]{null, I, E, A, O, U, L, R, W};
        G.connectors = new Phoneme[]{null, I, E, A, O, U, H, L, R, W};
        H.connectors = new Phoneme[]{null, I, E, A, O, U};
        J.connectors = new Phoneme[]{null, I, E, A, O, U};
        K.connectors = new Phoneme[]{null, I, E, A, O, U, H, L, R, W};
        L.connectors = new Phoneme[]{null, I, E, A, O, U, H, L, R, W};
    }

    private Phoneme[] connectors;
    private Phoneme[] connectorsAsLead;
    private String string;

    Phoneme(String string) {
        this.string = string;
    }
}

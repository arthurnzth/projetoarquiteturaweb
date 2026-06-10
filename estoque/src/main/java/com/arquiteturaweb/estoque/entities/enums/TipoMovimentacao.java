package com.arquiteturaweb.estoque.entities.enums;

public enum TipoMovimentacao {

    ENTRADA(1),
    SAIDA(2);

    private int code;

    private TipoMovimentacao(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TipoMovimentacao valueOf(int code) {
        for (TipoMovimentacao value : TipoMovimentacao.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid TipoMovimentacao code");
    }

}

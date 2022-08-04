package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConsult is a Querydsl query type for Consult
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConsult extends EntityPathBase<Consult> {

    private static final long serialVersionUID = 397713898L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConsult consult = new QConsult("consult");

    public final DateTimePath<java.sql.Timestamp> createdAt = createDateTime("createdAt", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath room = createString("room");

    public final QScript script;

    public final BooleanPath state = createBoolean("state");

    public QConsult(String variable) {
        this(Consult.class, forVariable(variable), INITS);
    }

    public QConsult(Path<? extends Consult> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConsult(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConsult(PathMetadata metadata, PathInits inits) {
        this(Consult.class, metadata, inits);
    }

    public QConsult(Class<? extends Consult> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.script = inits.isInitialized("script") ? new QScript(forProperty("script"), inits.get("script")) : null;
    }

}


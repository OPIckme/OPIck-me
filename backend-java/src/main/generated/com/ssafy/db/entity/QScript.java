package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScript is a Querydsl query type for Script
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QScript extends EntityPathBase<Script> {

    private static final long serialVersionUID = 1706849037L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScript script = new QScript("script");

    public final StringPath audioUrl = createString("audioUrl");

    public final DateTimePath<java.sql.Timestamp> createdAt = createDateTime("createdAt", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QQuestion question;

    public final StringPath scriptContent = createString("scriptContent");

    public final QUser user;

    public QScript(String variable) {
        this(Script.class, forVariable(variable), INITS);
    }

    public QScript(Path<? extends Script> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScript(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScript(PathMetadata metadata, PathInits inits) {
        this(Script.class, metadata, inits);
    }

    public QScript(Class<? extends Script> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.question = inits.isInitialized("question") ? new QQuestion(forProperty("question")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}


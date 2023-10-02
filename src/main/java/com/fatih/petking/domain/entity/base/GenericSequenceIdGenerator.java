package com.fatih.petking.domain.entity.base;

import java.util.Properties;
import org.hibernate.MappingException;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class GenericSequenceIdGenerator extends SequenceStyleGenerator {
    private static final String TARGET_TABLE = "target_table";

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        String sequencePerEntitySuffix = ConfigurationHelper.getString("sequence_per_entity_suffix", params, "_SEQ");
        boolean preferSequencePerEntity = ConfigurationHelper.getBoolean("prefer_sequence_per_entity", params, false);
        String defaultSequenceName = preferSequencePerEntity ? params.getProperty(TARGET_TABLE) + sequencePerEntitySuffix : "hibernate_sequence";
        params.setProperty("sequence_name", defaultSequenceName);
        super.configure(type, params, serviceRegistry);
    }
}

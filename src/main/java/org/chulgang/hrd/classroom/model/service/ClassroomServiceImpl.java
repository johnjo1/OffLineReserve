package org.chulgang.hrd.classroom.model.service;

import org.chulgang.hrd.classroom.dto.GetClassroomsResponse;
import org.chulgang.hrd.classroom.model.repository.ClassroomRepository;
import org.chulgang.hrd.classroom.model.repository.ClassroomRepositoryImpl;

public class ClassroomServiceImpl implements ClassroomService {
    private static final ClassroomService INSTANCE = new ClassroomServiceImpl(ClassroomRepositoryImpl.getInstance());
    private final ClassroomRepository classroomRepository;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public static ClassroomService getInstance() {
        return INSTANCE;
    }

    @Override
    public GetClassroomsResponse getClassrooms() {
        return GetClassroomsResponse.from(classroomRepository.findAll());
    }

    @Override
    public String getName(Long id) {
        return classroomRepository.findNameById(id);
    }
}

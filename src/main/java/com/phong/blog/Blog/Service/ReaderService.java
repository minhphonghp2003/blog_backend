package com.phong.blog.Blog.Service;

import com.phong.blog.Blog.Model.Reader;
import com.phong.blog.Blog.Repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;


    public Reader createReader(Reader reader) {
        return readerRepository.save(reader);
    }

    public Reader getReader(UUID id) {
        return readerRepository.findById(id).orElse(null);
    }

    public Page<Reader> getAllReader(Integer page) {
        Pageable pageable = PageRequest.of(page, 40);
        return readerRepository.findAll(pageable);
    }
}

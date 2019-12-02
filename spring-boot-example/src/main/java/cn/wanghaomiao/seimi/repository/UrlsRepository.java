package cn.wanghaomiao.seimi.repository;

import cn.wanghaomiao.seimi.model.Urls;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrlsRepository extends JpaRepository<Urls,Long> {


    Urls findOneByUrlEquals(String url);
}

package cn.wanghaomiao.seimi.repository;

import cn.wanghaomiao.seimi.model.Urls;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UrlsRepository extends JpaRepository<Urls,Long> {


    @Query("select u from Urls  u where  u.url=?1")
    Urls findOneByUrlEquals(String url);
}

package com.groupC.twitter.service.implementation;

import com.groupC.twitter.dto.HashtagDto;
import com.groupC.twitter.dto.TweetDto;
import com.groupC.twitter.model.Hashtag;
import com.groupC.twitter.model.Tweet;
import com.groupC.twitter.repository.HashtagPostRepository;
import com.groupC.twitter.repository.HashtagRepository;
import com.groupC.twitter.service.HashtagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HashtagServiceImpl implements HashtagService {

    @Autowired
    private HashtagRepository hashtagRepository;

    @Autowired
    private HashtagPostRepository hashtagPostRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<HashtagDto> getHashtags() {

        List<Hashtag> hashtags = hashtagRepository.findAll();
        List<HashtagDto> hashtagDtos = new ArrayList<>();

        hashtagDtos = hashtags.stream().map((hashtag)->modelMapper.map(hashtag, HashtagDto.class))
                .collect(Collectors.toList());
        return hashtagDtos;
    }

    @Override

    public List<TweetDto> getPosts(String name) {

        Hashtag hashtag = hashtagRepository.findByName(name);

        List<Tweet> tweets = new ArrayList<>();

        if(hashtag!=null){
            tweets = hashtagPostRepository.findByHashtagId(hashtag.getHashtagId());
        }
        List<TweetDto> tweetDtos = tweets.stream().map((tweet)->this.modelMapper.map(tweet,TweetDto.class))
                .collect(Collectors.toList());

        return tweetDtos;
    }

    @Override
    @Transactional
    public List<Hashtag> getHashtagsByNames(List<String> hashes) {

        List<Hashtag> existingHastag = hashtagRepository.findByNameIn(hashes);
        Set<String> existingNames = fetchNames(existingHastag);
        Set<String>newNames = new HashSet<>(hashes);
        newNames.removeAll(existingNames);
        List<Hashtag> toBeCreatedHashTags = new ArrayList<>(existingHastag);
        Optional.ofNullable(newNames).ifPresent(
                notPresentNames->{
                    notPresentNames.forEach(
                            newName->{
                                    Hashtag hashtag = new Hashtag();
                                    hashtag.setName(newName);
                                    toBeCreatedHashTags.add(hashtagRepository.save(hashtag));
                            }
                    );
                }
        );
        return toBeCreatedHashTags;
    }

    public Set<String> fetchNames(List<Hashtag>existingHastag){
        if(!CollectionUtils.isEmpty(existingHastag)){
            return existingHastag.stream().map(hname->hname.getName()).collect(Collectors.toSet());
        }
        return new HashSet<>();
    }
}

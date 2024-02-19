package com.java.tutorial.project.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.java.tutorial.project.domain.Account;
import com.java.tutorial.project.domain.Address;
import com.java.tutorial.project.repository.AddressRepository;
import com.java.tutorial.project.repository.entity.AddressEntity;
import com.java.tutorial.project.service.WebfluxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WebfluxServiceImpl implements WebfluxService {

    private static final List<Account> ACCOUNT_ARRAY_LIST = new ArrayList<>();
    private static final List<Integer> ADDRESS_ID_LIST = new ArrayList<>();
    private static final List<AddressEntity> ADDRESS_ENTITY_ID_LIST = new ArrayList<>();

    static {
        for (int i = 0; i < 10; i++) {
            ACCOUNT_ARRAY_LIST.add(
                Account.builder().accountId(ACCOUNT_ARRAY_LIST.isEmpty() ? 1234567L : RandomUtil.randomLong())
                    .accountName(RandomUtil.randomString(9)).nickname(RandomUtil.randomString(10))
                    .emailAddress(RandomUtil.randomString(10) + "@Gmail.com").phone(RandomUtil.randomInt(11)).build());
        }

        for (int i = 0; i < 10; i++) {
            ADDRESS_ENTITY_ID_LIST.add(AddressEntity.builder().cityId(RandomUtil.randomInt(600, 1000))
                .address(String.valueOf(RandomUtil.randomChinese()))
                .address2(String.valueOf(RandomUtil.randomChinese())).district(RandomUtil.randomString(10))
                .postalCode(RandomUtil.randomNumbers(9)).phone(String.valueOf(RandomUtil.randomInt(11))).build());
        }

        for (int i = 0; i < 2; i++) {
            ADDRESS_ID_LIST.add(RandomUtil.randomInt(600));
        }
    }

    private final AddressRepository addressRepository;

    @Override
    public String getInfo(Long id) {
        return ACCOUNT_ARRAY_LIST.stream().filter(e -> e.getAccountId().equals(1234567L)).collect(Collectors.toList())
            .toString();
    }

    @Override
    public Account getAccount(Long id) {
        //        val duration = Duration.ofMinutes(1);
        //        Flux.fromIterable(ACCOUNT_ARRAY_LIST)
        //                .mergeWith(Flux.interval(duration))
        //                .doOnNext(serviceA::someObserver)
        //                .map(d -> d * 2)
        //                .take(3)
        //                .onErrorResumeWith(errorHandler::fallback)
        //                .doAfterTerminate(serviceM::incrementTerminate)
        //                .subscribe(System.out::println);

        return ACCOUNT_ARRAY_LIST.stream().filter(e -> e.getAccountId().equals(1234567L)).collect(Collectors.toList())
            .get(0);
    }

    @Override
    public List<Account> getAllAccounts() {
        return ACCOUNT_ARRAY_LIST;
    }

    @Override
    public Flux<Address> getAddress(Integer addressId) {
        return null;
    }

    @Override
    public Flux<Address> getAllAddress() {
        return null;
    }

    @Override
    public Flux<String> getAddressToString() {

        LongAdder statsCancel = new LongAdder();
        //        AddressEntity AddressEntity = addressRepository.findAllByAddressId(addressId);
        //
        //        Disposable subscribe = Flux.just(addressRepository.findAllByAddressId(addressId)).subscribe(com.java.demo.repository.entity.AddressEntity::getAddress2);

        //Consumer<AddressEntity> consumer = addressRepository.findAllByAddressId(23123);

        /**
         *   .take(1)
         *                 .publishOn(Schedulers.boundedElastic())
         *                 .map(s -> {
         *                     try {
         *                         Thread.sleep(1000);
         *                     } catch (InterruptedException e) {
         *                         e.printStackTrace();
         *                     }
         *                     return "<letter:" + s.toString() + ">";
         *                 });
         */

        //        StringBuilder stringBuilder = new StringBuilder();
        //
        //        stringBuilder.append(addressRepository.findAll());
        //        for (Integer integer : ADDRESS_ID_LIST) {
        //            stringBuilder.append(addressRepository.findAll().toString());
        //        }

        //        Flux<String> flux =
        //                Flux.just(addressRepository.findAllByAddressId(addressId))
        //                        .doFinally(type -> {
        //                            if (type == SignalType.CANCEL)  // 2
        //                                statsCancel.increment();  // 3
        //                        })
        //                        .take(1)
        //                        .map(com.java.demo.repository.entity.AddressEntity::toString);

        //        List<String> arrayList = new ArrayList<>();
        //        arrayList.add("foo");
        //        arrayList.add("bar");
        //
        //        Flux.fromIterable(arrayList);

        Flux<Object> error = Flux.error(new IllegalStateException());

        Duration duration = Duration.ofSeconds(10);
        Flux<Long> interval = Flux.interval(duration);

        return Flux.fromIterable(ADDRESS_ID_LIST).map(s -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return addressRepository.findAllByAddressId(s);
        }).map(s -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.subscribe(AddressEntity::getAddress2);
        }).map(s -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "<letter:" + s + ">";
        });
    }

    //    private Consumer<AddressEntity> consumerAddressEntity(Integer id){
    //
    //
    //        Consumer<AddressEntity> consumer = (id) -> addressRepository.findAllByAddressId(id);
    //    }

    @Override
    public Flux<String> getAllAddressToString() {
        return null;
    }

    @Override
    public Mono<AddressEntity> saveAddressEntity() {

        AddressEntity addressEntity = ADDRESS_ENTITY_ID_LIST.get(0);
        return addressRepository.save(addressEntity);
    }
}

# spring-quartz-scheduler

비동기로 1초당 수행되도록 잡체인이 걸려 있지만, task가 완료되는지 확인하여 1한개의 스케줄링만 수행되도록 구성되어 있습니다. <br/>
테스크가 실패하고 쓰레드가 비정상 종료 되더라도, 다음 잡에서 영향 없이 원활히 실행되도록 하기 위해 이와 같은 쿼츠 스케줄러를 사용하기도 합니다. <br/>
세마포어 코드를 제거하면 이전 잡의 실행 여부와 상관 없이 비동기로 계속 실행됩니다. <br/>


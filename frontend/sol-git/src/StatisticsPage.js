import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Alert, Spinner } from 'react-bootstrap';
import config from './config';
import TotalCard from './CardComponent';
import CommitCard from './CommitCardComponent';
import IssueCard from './IssueCardComponent';
import PRCard from './PRCard';

function StatisticsPage() {
    const [stats, setStats] = useState({ commitCount: 0, issueCount: 0, pullRequestCount: 0 });
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        // 비동기 함수를 선언합니다.
        const fetchData = async () => {
            try {
                // 커밋 수, 이슈 수, 풀 리퀘스트 수에 대한 HTTP GET 요청을 수행합니다.
                // 각각의 요청 URL은 실제 경로로 교체해야 합니다.
                const commitResponse = await fetch(config.SERVER_URL + '/statistics');
                const data = await commitResponse.json(); // 이 예제에서는 응답이 직접 count를 반환한다고 가정합니다.

                // 상태를 업데이트합니다.
                setStats({
                    total: data.total,
                    commit: data.commit, // 서버로부터 받아온 커밋 카운트
                    issue: data.issue, // 서버로부터 받아온 이슈 카운트
                    pullRequest: data.pullRequest, // 서버로부터 받아온 풀 리퀘스트 카운트
                });
            } catch (error) {
                setError(error);
                console.log(error);
            } finally {
                setLoading(false); // 데이터 로딩이 끝났음을 표시합니다.
            }
        };

        // 비동기 함수를 실행합니다.
        fetchData();
    }, []); // 빈 배열은 이 효과가 마운트될 때 한 번만 실행되어야 함을 의미합니다.

    // 로딩, 에러, 또는 통계를 보여주는 UI를 렌더링합니다.
    if (loading) {
        return (
            <Container className="text-center my-5">
                <Spinner animation="border" role="status">
                    <span className="visually-hidden">Loading...</span>
                </Spinner>
            </Container>
        );
    }

    if (error) {
        return (<Container className="my-5">
            <Alert variant="danger">Error loading statistics</Alert>
        </Container>);
    }

    return (
        <Container className="my-5">
            <h1 class="display-3" style={{textAlign: 'center'}} className='mb-5'><strong>오늘의 솔깃</strong></h1>
            <Row className="center mb-3 justify-content-center align-items-center d-flex">
            <TotalCard headerColor='#fff' bodyColor='#832' title='오늘 총평' text={stats.total} />
            </Row>
            <Row className="justify-content-center">
                <Col className="mb-4">
                    <CommitCard headerColor='#fff' bodyColor='#832' title='Commits' text={stats.commit} />
                </Col>
                <Col className="mb-4">
                    <IssueCard headerColor='#fff' bodyColor='#832' title='Issues' text={stats.issue} />
                </Col>
                <Col >
                    <PRCard headerColor='#fff' bodyColor='#832' title='Pull Requests' text={stats.pullRequest} />
                </Col>
            </Row>
        </Container>
    );
}

export default StatisticsPage;

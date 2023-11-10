import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Card, ListGroup, ListGroupItem, Alert, Spinner } from 'react-bootstrap';
import config from './config';

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
                    commitCount: data.commitCount, // 서버로부터 받아온 커밋 카운트
                    issueCount: data.issueCount, // 서버로부터 받아온 이슈 카운트
                    pullRequestCount: data.pullRequestCount, // 서버로부터 받아온 풀 리퀘스트 카운트
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
            <Row className="justify-content-center">
                <Col lg={6} md={8}>
                    <Card>
                        <Card.Header as="h5">Statistics</Card.Header>
                        <ListGroup className="list-group-flush">
                            <ListGroupItem>Commit Count: {stats.commitCount}</ListGroupItem>
                            <ListGroupItem>Issue Count: {stats.issueCount}</ListGroupItem>
                            <ListGroupItem>Pull Request Count: {stats.pullRequestCount}</ListGroupItem>
                        </ListGroup>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
}

export default StatisticsPage;

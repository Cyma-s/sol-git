import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // useNavigate 훅을 임포트합니다.
import config from './config'; // 경로는 실제 config.js 파일 위치에 맞게 조정해야 합니다.
import { Form, Button, Container, Row, Col, Alert } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

function SignupPage() {
  const [nickname, setNickname] = useState('');
  const [error, setError] = useState(null);
  const navigate = useNavigate(); // history 객체를 얻습니다.

  const handleSignup = async (e) => {
    e.preventDefault(); // 폼 제출 시 페이지 새로고침 방지

    try {
      const response = await fetch(config.SERVER_URL + '/signup', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ nickname: nickname }),
      });

      if (!response.ok) {
        throw new Error(`Login failed: ${response.status}`);
      }

      const locationHeader = response.headers.get('Location');
      if (locationHeader) {
        const id = locationHeader.split('/').pop(); // URI의 마지막 부분을 ID로 가정합니다.
        console.log(id);
        localStorage.setItem('userId', id); // ID를 로컬 스토리지에 저장합니다.
        navigate('/statistics'); // '/statistics' 경로로 리다이렉션
      } else {
        // Location 헤더가 없는 경우, 오류 처리나 다른 로직을 수행합니다.
        console.error('Location header is missing');
        // 여기에 오류 처리 로직을 추가할 수 있습니다.
      }
    } catch (error) {
      setError(error.message);
    }
  };

  return (
    <Container className="mt-5">
      <Row>
        <Col md={{ span: 6, offset: 3 }}>
          <h2 className="text-center mb-4">⚡️ SOLGIT 확인하기 ⚡️</h2>
          <Form onSubmit={handleSignup}>
            <Form.Group controlId="text" className="mb-3">
              <Form.Label>Github 아이디</Form.Label>
              <Form.Control type="nickname" placeholder="Enter Github ID" value={nickname} onChange={(e) => setNickname(e.target.value)} />
            </Form.Group>
            <Button variant="primary" type="submit" className="w-100">
              등록하기
            </Button>
          </Form>
          {error && <Alert variant="danger" className="mt-3">{error}</Alert>}
        </Col>
      </Row>
    </Container>
  );
}

export default SignupPage;

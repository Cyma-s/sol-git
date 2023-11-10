import styled from 'styled-components';

// Styled 컴포넌트 정의
const StyledCard = styled.div.attrs(() => ({
  className: 'card border-success text-bg-dark rounded-4 border-2 mb-3',
}))`
  font-size: 20px;
  text-align: center;
  max-width: 400px;
`;

const CardHeader = styled.div.attrs(() => ({
  className: 'card-header',
}))`
color: ${(props) => props.color || 'inherit'}; // 색상을 props에서 받아옵니다. 기본값은 'inherit'입니다.
`;

const CardBody = styled.div.attrs(() => ({
  className: 'card-body',
}))`
color: ${(props) => props.color || 'inherit'}; // 색상을 props에서 받아옵니다. 기본값은 'inherit'입니다.
`;

const CardTitle = styled.h5.attrs(() => ({
  className: 'card-title',
}))`
color: white;
`;

const CardText = styled.p.attrs(() => ({
  className: 'card-text',
}))``;

// 사용 예
function CommitCard({ headerColor, bodyColor, title, text }) {
  return (
    <StyledCard>
      <CardHeader color={headerColor}><strong>{title}</strong></CardHeader>
      <CardBody color={bodyColor}>
        <CardTitle>{text}</CardTitle>
        <CardText></CardText>
      </CardBody>
    </StyledCard>
  );
}

export default CommitCard;

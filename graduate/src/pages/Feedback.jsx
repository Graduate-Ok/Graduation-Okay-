import '../css/Feedback.css';

/**
 * @description 피드백 컴포넌트
 */
function Feedback() {
    function handleFeedback() {
        alert('피드백이 제출되었습니다. 감사합니다😊');
    }
    return (
        <>
            <div className="feedback">
                <p className="feedback__header">피드백하기</p>
                <form
                    method="post"
                    className="feedback-Form"
                    data-email="example@email.net"
                    target="iframe1"
                    action="https://script.google.com/macros/s/AKfycbx__oFv68iMibnKPQyYoKsXnq1A8-uy07Z63t4NqELOcyCVpjIK-sOMTv9YT5248jVQ/exec"
                >
                    <div className="feedback__div">
                        <input
                            id="email"
                            name="email"
                            type="email"
                            placeholder="your.email@email.com"
                            required
                        />

                        <textarea
                            id="msg"
                            name="msg"
                            rows="10"
                            placeholder="의견을 작성해주세요 "
                            required
                        />

                        <button type="submit" onClick={handleFeedback}>
                            피드백 보내기
                        </button>
                    </div>
                </form>
            </div>
            <script
                data-cfasync="false"
                type="text/javascript"
                src="form-submission-handler.js"
            ></script>
            <iframe id="iframe1" name="iframe1"></iframe>
        </>
    );
}

export default Feedback;

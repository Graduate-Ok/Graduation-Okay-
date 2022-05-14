import '../css/GraduateOk.css';


function GraduateOk() {

    return (
        <>
            <main>
                <section className="section">
                    <div className='Graduate__check'>
                        <div className='Lack__Graduate__Credit'>
                            졸업 학점 20 미달
                        </div>
                        <div className='Lack__MajorCredit'>
                            전공 학점 20 미달
                        </div>
                        <div className='Lack__KyCredit'>
                            교양 학점 20 미달
                        </div>

                    </div>
                </section>
            </main>
        </>
    );
}

export default GraduateOk;
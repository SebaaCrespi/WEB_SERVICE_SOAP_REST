<?php

namespace App\Controller;

use App\Entity\StudentExam;
use App\Form\StudentExamType;
use App\Repository\StudentExamRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/student/exam")
 */
class StudentExamController extends AbstractController
{
    /**
     * @Route("/", name="app_student_exam_index", methods={"GET"})
     */
    public function index(StudentExamRepository $studentExamRepository): Response
    {
        return $this->render('student_exam/index.html.twig', [
            'student_exams' => $studentExamRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="app_student_exam_new", methods={"GET", "POST"})
     */
    public function new(Request $request, StudentExamRepository $studentExamRepository): Response
    {
        $studentExam = new StudentExam();
        $form = $this->createForm(StudentExamType::class, $studentExam);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $studentExamRepository->add($studentExam, true);

            return $this->redirectToRoute('app_student_exam_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('student_exam/new.html.twig', [
            'student_exam' => $studentExam,
            'form' => $form,
        ]);
    }

    /**
     * @Route("/{id}", name="app_student_exam_show", methods={"GET"})
     */
    public function show(StudentExam $studentExam): Response
    {
        return $this->render('student_exam/show.html.twig', [
            'student_exam' => $studentExam,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="app_student_exam_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, StudentExam $studentExam, StudentExamRepository $studentExamRepository): Response
    {
        $form = $this->createForm(StudentExamType::class, $studentExam);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $studentExamRepository->add($studentExam, true);

            return $this->redirectToRoute('app_student_exam_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('student_exam/edit.html.twig', [
            'student_exam' => $studentExam,
            'form' => $form,
        ]);
    }

    /**
     * @Route("/{id}", name="app_student_exam_delete", methods={"POST"})
     */
    public function delete(Request $request, StudentExam $studentExam, StudentExamRepository $studentExamRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$studentExam->getId(), $request->request->get('_token'))) {
            $studentExamRepository->remove($studentExam, true);
        }

        return $this->redirectToRoute('app_student_exam_index', [], Response::HTTP_SEE_OTHER);
    }
}
